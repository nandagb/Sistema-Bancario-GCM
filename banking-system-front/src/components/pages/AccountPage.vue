<template>
    <div>
        <label for="numero">Número da Conta</label>
        <input id="numero" v-model="accountNumber" />
    </div>
    <div>
        <h1 v-if="balance != null">
            Dados da conta {{searchedAccountNumber}}     <br/><br/> 
        </h1>
        <div v-if="balance != null">
            <span style="color: green;"> Número: {{ accountNumber }} </span>
            <span style="color: green;"> Tipo: {{ type }} </span>
            <span style="color: green;"> Saldo: {{ balance }}R$ </span>
            <span v-if="bonus" style="color: green;"> Bônus: {{ bonus }}R$ </span>
        </div>
    </div>
    <AppButton @click="handleGetAccount">
        Consultar Conta
    </AppButton>
</template>
<script setup>
import AppButton from '../util/AppButton.vue';
import {currentAccount, getAccount} from '@/services/accountService.js'
import {ref} from 'vue'
let accountNumber
let searchedAccountNumber = ref()
let balance = ref()
let type = ref()
let bonus = ref()

let handleGetAccount = async () =>{
    try{
        currentAccount.value = await getAccount(accountNumber)
        balance.value = currentAccount.value.balance
        if(currentAccount.value.type == "current_account"){
            type.value = "Conta Corrente"
        }
        else if(currentAccount.value.type == "bonus_account"){
            type.value = "Conta Bônus"
        }
        else{
            type.value = "Conta Poupança"
        }
        searchedAccountNumber.value = currentAccount.value.number
        bonus.value = currentAccount.value.bonus

    }
    catch(e){
        console.error(e)
    }
}
</script>