import "./App.css";
import { useState, useEffect } from "react";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import Warehouses from "./pages/Warehouses/Warehouses";
import * as warehouseService from "./services/WarehouseService";
import NavBar from "./components/navbar/Navbar";
import Home from"./pages/Home";
import AddWarehouse from "./pages/Warehouses/AddWarehouse";
import GetWarehouse from "./pages/Warehouses/GetWarehouse";
import UpdateWarehouse from "./pages/Warehouses/UpdateWarehouse";

function App() {
  const [warehouses, setWarehouses] = useState([])
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAllWarehouses = async () => {
      const data = await warehouseService.index()
      console.log('Warehouse Data from useeffect:', data)
      setWarehouses(data)
    }
    fetchAllWarehouses()
  }, [])

  const handleAddWarehouse = async (warehouseFormData) => {
    const addedWarehouse = await warehouseService.create(warehouseFormData)
    console.log('Added warehouse: ' + addedWarehouse.warehouseId + addedWarehouse.capacity + addedWarehouse.warehouseName)
    const addedWarehouses = [addedWarehouse, ...warehouses]
    setWarehouses(addedWarehouses)
    navigate('/warehouses')
  }

  const handleUpdateWarehouse = async (warehouseFormData) => {
    console.log('Updating warehouse...')
    const updatedWarehouse = await warehouseService.update(warehouseFormData)
    console.log('Updated warehouse successfully.')
    const updatedWarehouses = warehouses.map((warehouse) => (warehouseFormData.id === warehouse.id ? updatedWarehouse : warehouse))
    setWarehouses(updatedWarehouses)
    navigate(`/warehouses/warehouse/${warehouseFormData.id}`)
  }

  const handleDeleteWarehouse = async (warehouseId) => {
    console.log('Deleting warehouse...')
    await warehouseService.deleteWarehouse(warehouseId)
    console.log('Deleted warehouse successfully.')
    const updatedWarehouses= warehouses.filter((warehouse) => warehouse.id !== parseInt(warehouseId))
    setWarehouses(updatedWarehouses)
    navigate('/warehouses')
  }
  
  return (
    <>
      <NavBar/>
      <Routes>
        <Route path="/" element={<Home />}/>
        <Route path="/warehouses" element={<Warehouses warehouses={warehouses}/>}/>
        <Route path="warehouses/warehouse/add" element={<AddWarehouse handleAddWarehouse={handleAddWarehouse}/>}/>
        <Route path="warehouses/warehouse/:warehouseId/edit" element={<UpdateWarehouse handleUpdateWarehouse={handleUpdateWarehouse}/>}/>
        <Route path="warehouses/warehouse/:warehouseId" element={<GetWarehouse handleDeleteWarehouse={handleDeleteWarehouse}/> } />
      </Routes>
    </>
  )
}

export default App;
