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
import {addCredit} from '@/services/accountService.js'
import {ref} from 'vue'
let accountNumber
let value
let sent = ref()

let handleCredit = async () =>{
    try{
        await addCredit(accountNumber, value)
        sent.value = true
    }
    catch(e){
        console.error(e)
        sent.value = false
    }
}
</script>