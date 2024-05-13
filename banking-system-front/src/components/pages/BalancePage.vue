<template>
    <div>
        <label for="numero">Número da Conta</label>
        <input id="numero" v-model="accountNumber" />
    </div>
    <div>
        <h1 v-if="balance != null">
            O saldo da conta {{searchedAccountNumber}} é de: <br/><br/> <span style="color: green;"> {{ balance }}R$ </span>
        </h1>
    </div>
    <AppButton @click="handleGetBalance">
        Consultar Saldo
    </AppButton>
</template>
<script setup>
import AppButton from '../util/AppButton.vue';
import {consultBalance} from '@/services/accountService.js'
import {ref} from 'vue'
let accountNumber
let searchedAccountNumber = ref()
let balance = ref()

let handleGetBalance = async () =>{
    try{
        searchedAccountNumber = accountNumber
        balance.value = await consultBalance(accountNumber)
    }
    catch(e){
        console.error(e)
    }
}
</script>