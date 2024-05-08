<template>
    <div>
        <label for="numero">Número da Conta</label>
        <input id="numero" v-model="accountNumber" />
    </div>
    <AppButton @click="handleCreateAccount(accountNumber)">Criar Conta</AppButton>

</template>

<script setup>
import { useRouter } from 'vue-router';
import AppButton from '../util/AppButton.vue';
import { createSavingsAccount } from '@/services/accountService.js'

let router = useRouter()
const handleCreateAccount = async (accountNumber) => {
    try {
        await createSavingsAccount(accountNumber)
        router.back()
    }
    catch(e){
        console.error(e);
    }
}

let accountNumber
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