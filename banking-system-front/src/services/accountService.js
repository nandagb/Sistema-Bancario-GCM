import axios from "axios"
const backendURL= process.env.VUE_APP_ACCOUNT_BACKEND;

export const createAccount = async (accountNumber) => {
    await axios.post(`${backendURL}/create`, {AccountNumber: parseInt(accountNumber)})
}

export const debit = async (accountNumber, value) => {
    await axios.post(`${backendURL}/debit`, {AccountNumber: parseInt(accountNumber), Value: parseInt(value)})
}

export const consultBalance = async (accountNumber) =>{
    let res = await axios.get(`${backendURL}/get_balance?accountNumber=${accountNumber}`)
    return res.data
}

export const transfer = async (accountNumberOrigin, accountNumberDestination, value) =>{
    await axios.post(`${backendURL}/transfer`, {AccountNumberOrigin: parseInt(accountNumberOrigin), AccountNumberDestination: parseInt(accountNumberDestination), Value: parseInt(value)})
}

export const addCredit = async (accountNumber, value) =>{
    let res = await axios.post(`${backendURL}/credit`, {AccountNumber: parseInt(accountNumber), Value: parseInt(value)})
    return res.data
}