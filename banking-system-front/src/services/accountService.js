import axios from "axios"
import { ref } from 'vue'
const backendURL = process.env.VUE_APP_ACCOUNT_BACKEND;

export const currentAccount = ref()

/* REQUISIÇÕES HTTP */

export const getAccount = async (accountNumber) => {
    let res = await axios.get(`${backendURL}/get_account?AccountNumber=${parseInt(accountNumber)}`)
    return res.data
}

export const createBonusAccount = async (accountNumber) => {
    await axios.post(`${backendURL}/create/bonus_account`, { AccountNumber: parseInt(accountNumber) })
}
export const createCurrentAccount = async (accountNumber) => {
    await axios.post(`${backendURL}/create/current`, { AccountNumber: parseInt(accountNumber) })
}

export const createSavingsAccount = async (accountNumber, accountBalance) => {
    await axios.post(`${backendURL}/create/savings`, { AccountNumber: parseInt(accountNumber), AccountBalance: parseFloat(accountBalance) })
}

export const debit = async (accountNumber, value) => {
    await axios.post(`${backendURL}/debit`, { AccountNumber: parseInt(accountNumber), Value: parseInt(value) })
}

export const consultBalance = async (accountNumber) => {
    let res = await axios.get(`${backendURL}/get_balance?accountNumber=${accountNumber}`)
    return res.data
}

export const transfer = async (accountNumberOrigin, accountNumberDestination, value) => {
    await axios.post(`${backendURL}/transfer`, { AccountNumberOrigin: parseInt(accountNumberOrigin), AccountNumberDestination: parseInt(accountNumberDestination), Value: parseInt(value) })
}

export const addCredit = async (accountNumber, value) => {
    let res = await axios.post(`${backendURL}/credit`, { AccountNumber: parseInt(accountNumber), Value: parseInt(value) })
    return res.data
}

export const addCreditBonusAccount = async (accountNumber, value) => {
    await axios.post(`${backendURL}/credit/bonus_account`, { AccountNumber: parseInt(accountNumber), Value: parseInt(value) })
}

export const yieldInterest = async (interestRate) => {
    let res = await axios.post(`${backendURL}/yield_interest`, { InterestRate: parseFloat(interestRate) })
    return res.data
}