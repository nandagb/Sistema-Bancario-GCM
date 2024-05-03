<script setup>
</script>

<template>
    <button>
        <slot>

        </slot>
    </button>
</template>

<style scoped>
button {
    background-color: transparent;
    text-align: center;
    border: 2px solid green;
    border-radius: 10px;
    font-size: 1.5em;
    transition: 0.3s all ease;
}

button:hover {
    background-color: green;
    color: white;
    cursor: pointer;
}
</style>