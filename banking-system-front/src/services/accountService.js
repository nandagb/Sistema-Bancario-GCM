import axios from "axios"
const backendURL= process.env.VUE_APP_ACCOUNT_BACKEND;

export const createAccount = async (accountNumber) => {
    await axios.post(`${backendURL}/create`, {AccountNumber: parseInt(accountNumber)})
}

export const debit = async (accountNumber, debitValue) => {
    await axios.post(`${backendURL}/debit`, {AccountNumber: parseInt(accountNumber), DebitValue: parseInt(debitValue)})
}