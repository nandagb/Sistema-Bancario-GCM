<template>
    <div>
        <label for="number">Número da Conta</label>
        <input id="number" v-model="accountNumber" />
        <label for="value">Valor do Débito</label>
        <input id="value" v-model="value" />
    </div>
    <AppButton @click="handleDebit(accountNumber, value)">Debitar</AppButton>

</template>

<script setup>
import { useRouter } from 'vue-router';
import AppButton from '../util/AppButton.vue';
import { debit } from '@/services/accountService.js'

let router = useRouter()
const handleDebit = async (accountNumber, value) => {
    console.log("accountNumber: " + accountNumber);
    console.log("value: " + value);
    try {
        await debit(accountNumber, value)
        router.back()
    }
    catch(e){
        console.error(e);
    }
}

let accountNumber
let value
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