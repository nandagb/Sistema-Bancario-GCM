<template>
    <div>
        <label for="numero">Número da Conta</label>
        <input id="numero" v-model="accountNumber" />
    </div>
    <div>
        <h1 v-if="balance != null">
            O saldo da conta {{searchedAccountNumber}} é de: <br/><br/> <span style="color: green;"> {{ balance }}R$ </span>
        </h1>
        <h2 v-if="balance">
            O bônus da conta é de: <br/><br/> <span style="color: green;"> {{ bonus }}R$ </span>
        </h2>
    </div>
    <AppButton @click="handleGetBalance">
        Consultar Saldo
    </AppButton>
</template>
<script setup>
import AppButton from '../util/AppButton.vue';
import {currentAccount, getAccount} from '@/services/accountService.js'
import {ref} from 'vue'
let accountNumber
let searchedAccountNumber = ref()
let balance = ref()
let bonus = ref()

let handleGetBalance = async () =>{
    try{
        currentAccount.value = await getAccount(accountNumber)
        balance.value = currentAccount.value.balance
        bonus.value = currentAccount.value.bonus
    }
    catch(e){
        console.error(e)
    }
}
</script>