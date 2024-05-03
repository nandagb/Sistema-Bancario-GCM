import { createApp } from 'vue'
import App from './App.vue'
import { createMemoryHistory, createRouter } from 'vue-router'
import SignUp from './components/pages/SignUp.vue'
import IndexPage from './components/pages/IndexPage.vue'

const routes = [
    {
        path:"/",
        name:"index",
        component:IndexPage
    },
    {
        path:"/signup",
        name:"signup",
        component:SignUp
    }
]

const router = createRouter({
    history: createMemoryHistory(),
    routes
}
)

createApp(App).use(router).mount('#app')
