import { createApp } from 'vue'
import App from './App.vue'
import { createMemoryHistory, createRouter } from 'vue-router'
import SignUp from './components/pages/SignUp.vue'
import IndexPage from './components/pages/IndexPage.vue'
import DebitPage from './components/pages/DebitPage.vue'
import BalancePage from './components/pages/BalancePage.vue'
import TransferPage from './components/pages/TransferPage.vue'


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
    },
    {
        path:"/debit",
        name:"debit",
        component:DebitPage
    },
    {
        path:"/balance",
        name:"balance",
        component:BalancePage
    },
    {
        path:"/transfer",
        name:"transfer",
        component:TransferPage
    }
]

const router = createRouter({
    history: createMemoryHistory(),
    routes
}
)

createApp(App).use(router).mount('#app')
