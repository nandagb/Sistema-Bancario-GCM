<template>
    <div>
        <label for="origin">Número da Conta de Origem</label>
        <input id="origin" v-model="accountNumberOrigin" />
        <label for="destination">Número da Conta de Destino</label>
        <input id="destination" v-model="accountNumberDestination" />
        <label for="value">Valor da Transferência</label>
        <input id="value" v-model="value" />
    </div>
    <div v-if="sent">
        <h1>
            Operação Realizada com Sucesso!
        </h1>
    </div>
    <AppButton @click="handleTransfer(accountNumberOrigin, accountNumberDestination, value)">Transferir</AppButton>

</template>

<script setup>
import AppButton from '../util/AppButton.vue';
import { transfer } from '@/services/accountService.js'
import {ref} from 'vue'

const handleTransfer = async (accountNumberOrigin, accountNumberDestination, value) => {

    try {
        await transfer(accountNumberOrigin, accountNumberDestination, value)
        sent.value = true
    }
    catch(e){
        console.error(e)
        sent.value = false
    }
}

let accountNumberOrigin
let accountNumberDestination
let value
let sent = ref()
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