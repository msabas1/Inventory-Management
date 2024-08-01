const BASE_URL = "http://localhost:8080/warehouse";

  async function index() {
    try {
      const res = await fetch(BASE_URL)
      return res.json()
    } catch (error) {
      console.log(error)
    }
  }
  
  async function show(warehouseId){
    try{
      const res = await fetch(`${BASE_URL}/${warehouseId}`)
      return res.json()
    }catch(error){
      console.log(error)
    }
  }
  
  async function create(warehouseFormData) {
    try {
      const res = await fetch(`${BASE_URL}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(warehouseFormData),
      })
      return res.json()
    } catch (error) {
      console.log("error")
    }
  }
  
  async function update(warehouseFormData) {
    try {
      const res = await fetch(`${BASE_URL}/${warehouseFormData.warehouseId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(warehouseFormData),
      })
      return res.json()
    } catch (error) {
      console.log(error)
    }
  }
  
  async function deleteWarehouse(warehouseId){
    try {
      const res = await fetch(`${BASE_URL}/${warehouseId}`, {
        method: 'DELETE',
      })
    } catch (error) {
      console.log(error)
    }
  }
  
  
export {index, show, create, update, deleteWarehouse};