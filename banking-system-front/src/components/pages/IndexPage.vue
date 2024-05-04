<template>
  <div>
    <RouterLink to="/signup">
      <AppButton>Criar Conta</AppButton>
    </RouterLink>
  </div>
    
  <div>
    <RouterLink to="/debit">
      <AppButton>Débito</AppButton>
    </RouterLink>
  </div>
  

  <div>
    <RouterLink to="/balance">
      <AppButton>Consultar Saldo</AppButton>
    </RouterLink>
  </div>

  <RouterLink to="/transfer">
    <AppButton>Transferência</AppButton>
  </RouterLink>
</template>

<script setup>
import AppButton from '@/components/util/AppButton.vue'
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

main {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}

div, AppButton {
  margin-bottom: 3em;
}
</style>