<template>
    <div>
        <label for="numero">Número da Conta</label>
        <input id="numero" v-model="accountNumber" />

        <label for="numero">Saldo Inicial</label>
        <input id="numero" v-model="initialBalance" />
    </div>
    <AppButton @click="handleCreateAccount(accountNumber)">Criar Conta</AppButton>

</template>

<script setup>
import { useRouter } from 'vue-router';
import AppButton from '../util/AppButton.vue';
import { addCredit, createCurrentAccount } from '@/services/accountService.js'
import {ref} from 'vue'

let router = useRouter()
const handleCreateAccount = async (accountNumber) => {
    try {
        if (initialBalance.value >= 0) {
            await createCurrentAccount(accountNumber)
            await addCredit(accountNumber, initialBalance.value)
            router.back()
        }
    }
    catch (e) {
        console.error(e);
    }
}

let accountNumber
let initialBalance = ref(0)
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