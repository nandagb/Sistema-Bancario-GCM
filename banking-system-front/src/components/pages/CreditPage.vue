<template>
    <div>
        <label for="numero">Número da Conta</label>
        <input id="numero" v-model="accountNumber" />
    </div>
    <div>
        <label for="value">Valor</label>
        <input id="value" v-model="value" />
    </div>
    <div v-if="sent">
        <h1>
            Operação Realizada com Sucesso!
        </h1>
    </div>
    <AppButton @click="handleCredit">
        Adicionar Crédito
    </AppButton>
</template>
<script setup>
import AppButton from '../util/AppButton.vue';
import {addCredit, currentAccount, addCreditBonusAccount, getAccount} from '@/services/accountService.js'
import {ref} from 'vue'
let accountNumber
let value
let sent = ref()

let handleCredit = async () =>{
    try{
        currentAccount.value = await getAccount(accountNumber)
        console.log(currentAccount.value)
        switch(currentAccount.value.type){
            case "bonus_account":
                await addCreditBonusAccount(accountNumber, value)
                break
            default:
                await addCredit(accountNumber, value)
                break
        }
        sent.value = true
    }
    catch(e){
        console.error(e)
        sent.value = false
    }
}
</script>