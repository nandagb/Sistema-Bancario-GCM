<template>
  <main>
    <div v-if="$route.name != 'index'">
      <AppButton @click="goBack">Voltar</AppButton>
    </div>

    <RouterView>

    </RouterView>

  </main>
</template>

<script setup>
import { useRouter } from 'vue-router';
import AppButton from './components/util/AppButton.vue';
let router = useRouter()
const goBack = () =>{
  router.back()
}

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
  align-items: flex-end;
}

div {
  margin-bottom: 3em;
}
</style>
