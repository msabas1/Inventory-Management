const API_URL = "http://localhost:8080/item";

 async function getAllItems() {
    try {
      const res = await fetch(API_URL)
      return res.json()
    } catch (error) {
      console.log(error)
    }
  }
  
  async function getItemById(itemId){
    try{
      const res = await fetch(`${API_URL}/${itemId}`)
      return res.json()
    }catch(error){
      console.log(error)
    }
  }
  
  async function addItem(itemFormData) {
    try {
      const res = await fetch(`${API_URL}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(itemFormData),
      })
      return res.json()
    } catch (error) {
      console.log(error)
    }
  }
  
  async function updateItem(itemFormData) {
    try {
      const res = await fetch(`${API_URL}/${itemFormData.itemId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(itemFormData),
      })
      return res.json()
    } catch (error) {
      console.log(error)
    }
  }
  
  async function deleteItem(itemId){
    try {
      const res = await fetch(`${API_URL}/${itemId}`, {
        method: 'DELETE',
      })
    } catch (error) {
      console.log(error)
    }
  }
  
  
export {getAllItems, getItemById, addItem, updateItem, deleteItem};