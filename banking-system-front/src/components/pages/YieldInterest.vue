<template>
    <div>
        <label for="numero">Porcentagem dos Juros</label>
        <input id="numero" v-model="interestRate" />
    </div>
    <AppButton @click="handleYieldInterest">
        Render Juros
    </AppButton>
    <div v-if="accounts != null && accounts.length > 0">
        <h1>Contas atualizadas:</h1>
        <div v-for="(account, index) in accounts" :key="index">
            <p>Número: {{account.number}}, Saldo: {{account.balance}}</p> 
        </div>
    </div>
</template>
<script setup>
import AppButton from '../util/AppButton.vue';
import {yieldInterest} from '@/services/accountService.js'
import {ref} from 'vue'
let interestRate
let accounts = ref([])

let handleYieldInterest = async () =>{
    try{
        accounts.value = await yieldInterest(interestRate)
        console.log("accounts: " + JSON.stringify(accounts))
    }
    catch(e){
        console.error(e)
    }
}
</script>