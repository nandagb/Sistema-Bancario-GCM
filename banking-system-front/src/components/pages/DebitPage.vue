<template>
    <div>
        <label for="number">Número da Conta</label>
        <input id="number" v-model="accountNumber" />
        <label for="debit">Valor do Débito</label>
        <input id="debit" v-model="debitValue" />
    </div>
    <AppButton @click="handleDebit(accountNumber, debitValue)">Debitar</AppButton>

</template>

<script setup>
import { useRouter } from 'vue-router';
import AppButton from '../util/AppButton.vue';
import { debit } from '@/services/accountService.js'

let router = useRouter()
const handleDebit = async (accountNumber, debitValue) => {
    try {
        await debit(accountNumber, debitValue)
        router.back()
    }
    catch(e){
        console.error(e);
    }
}

let accountNumber
let debitValue
</script>

<style>
input {
    width: 20em;
    height: 2em;
    border-radius: 10px;
    border: 2px solid green;
}

div {
    display: flex;
    flex-direction: column;
}
</style>