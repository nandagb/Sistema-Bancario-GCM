<template>
  <div>
    <RouterLink to="/signup/current">
      <AppButton>Criar Conta Corrente</AppButton>
    </RouterLink>
  </div>

  <div>
    <RouterLink to="/signup/savings">
      <AppButton>Criar Conta Poupanca</AppButton>
    </RouterLink>
  </div>

  <div>
    <RouterLink to="/signup/bonus_account">
      <AppButton>Criar Conta Bônus</AppButton>
    </RouterLink>
  </div>

  <div>
    <RouterLink to="/get_account">
      <AppButton>Consultar Conta</AppButton>
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

  <div>
    <RouterLink to="/transfer">
      <AppButton>Transferência</AppButton>
    </RouterLink>
  </div>

  <div>
    <RouterLink to="/credit">
      <AppButton>Adicionar Crédito</AppButton>
    </RouterLink>
  </div>

  <div>
    <RouterLink to="/yield_interest">
      <AppButton>Render Juros</AppButton>
    </RouterLink>
  </div>
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