import { createApp } from 'vue'
import App from './App.vue'
import { createMemoryHistory, createRouter } from 'vue-router'
import SignUpCurrent from './components/pages/SignUpCurrent.vue'
import SignUpSavings from './components/pages/SignUpSavings.vue'
import IndexPage from './components/pages/IndexPage.vue'
import DebitPage from './components/pages/DebitPage.vue'
import BalancePage from './components/pages/BalancePage.vue'
import TransferPage from './components/pages/TransferPage.vue'
import CreditPage from './components/pages/CreditPage.vue'
import SignUpBonusAccount from './components/pages/SignUpBonusAccount.vue'
import YieldInterest from './components/pages/YieldInterest.vue'


const routes = [
    {
        path:"/",
        name:"index",
        component:IndexPage
    },
    {
        path:"/signup/current",
        name:"signup_current",
        component:SignUpCurrent
    },
    {
        path:"/signup/savings",
        name:"signup_savings",
        component:SignUpSavings
    },
    {
        path:"/signup/bonus_account",
        name:"signupBonusAccount",
        component:SignUpBonusAccount
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
    },
    {
        path:"/credit",
        name:"credit",
        component:CreditPage
    },
    {
        path:"/yield_interest",
        name:"yield_interest",
        component:YieldInterest
    }
]

const router = createRouter({
    history: createMemoryHistory(),
    routes
}
)

createApp(App).use(router).mount('#app')
