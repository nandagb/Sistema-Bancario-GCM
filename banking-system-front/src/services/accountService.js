import axios from "axios"
const backendURL= process.env.VUE_APP_ACCOUNT_BACKEND;

export const createAccount = async (accountNumber) => {
    await axios.post(`${backendURL}/create`, {AccountNumber: parseInt(accountNumber)})
}