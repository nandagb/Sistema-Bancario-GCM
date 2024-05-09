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
import { debit, currentAccount, getAccount, debitBonusAccount } from '@/services/accountService.js'

let router = useRouter()
const handleDebit = async (accountNumber, value) => {
    try {
        currentAccount.value = await getAccount(accountNumber)
        switch(currentAccount.value.type){
            case "bonus_account":
                await debitBonusAccount(accountNumber)
                break
            default:
                await debit(accountNumber, value)
                break
        }
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