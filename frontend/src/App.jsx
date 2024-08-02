import "./App.css";
import { useState, useEffect } from "react";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import Warehouses from "./pages/Warehouses/Warehouses";
import * as warehouseService from "./services/WarehouseService";
import * as itemService from "./services/ItemService";
import NavBar from "./components/navbar/Navbar";
import Home from"./pages/Home";
import AddWarehouse from "./pages/Warehouses/AddWarehouse";
import GetWarehouse from "./pages/Warehouses/GetWarehouse";
import UpdateWarehouse from "./pages/Warehouses/UpdateWarehouse";
import Items from "./pages/Items/Items";
import AddItem from "./pages/Items/AddItem";
import GetItem from "./pages/Items/GetItem";
import UpdateItem from "./pages/Items/UpdateItem";

function App() {
  const [warehouses, setWarehouses] = useState([])
  const [items, setItems] = useState([])
  const navigate = useNavigate();

  //handle the warehouse functionalities
  useEffect(() => {
    const fetchAllWarehouses = async () => {
      const data = await warehouseService.getAllWarehouses()
      console.log('Warehouses Data from useEffect:', data)
      setWarehouses(data)
    }
    fetchAllWarehouses()
  }, [])

  const handleAddWarehouse = async (warehouseFormData) => {
    const addedWarehouse = await warehouseService.addWarehouse(warehouseFormData)
    console.log('Added warehouse successfully. id: ' + addedWarehouse.warehouseId + ', capacity: ' + addedWarehouse.capacity + ', name: ' + addedWarehouse.warehouseName)
    const addedWarehouses = [addedWarehouse, ...warehouses]
    setWarehouses(addedWarehouses)
    navigate('/warehouses')
  }

  const handleUpdateWarehouse = async (warehouseFormData) => {
    console.log('Updating warehouse...')
    const updatedWarehouse = await warehouseService.updateWarehouse(warehouseFormData)
    console.log('Updated warehouse successfully.')
    const updatedWarehouses = warehouses.map((warehouse) => (warehouseFormData.id === warehouse.id ? updatedWarehouse : warehouse))
    setWarehouses(updatedWarehouses)
    navigate(`/warehouses`)
  }

  const handleDeleteWarehouse = async (warehouseId) => {
    console.log('Deleting warehouse...')
    await warehouseService.deleteWarehouse(warehouseId)
    console.log('Deleted warehouse successfully.')
    const updatedWarehouses= warehouses.filter((warehouse) => warehouse.id !== parseInt(warehouseId))
    setWarehouses(updatedWarehouses)
    navigate('/warehouses')
  }

  //handle the item functionalities
  useEffect(() => {
    const fetchAllItems = async () => {
      const data = await itemService.getAllItems()
      console.log('Items Data from useEffect:', data)
      data.map(item => {
        if (item.warehouse !== null){
        return item.warehouse = item.warehouse.warehouseId
      }
        return item
      })
      setItems(data)
    }
    fetchAllItems()
  }, [])

  const handleAddItem = async (itemFormData) => {
    let warehouseId = parseInt(itemFormData.warehouse)
    const itemWarehouse = warehouses.filter(warehouse => warehouse.warehouseId === warehouseId)
    itemFormData.warehouse = itemWarehouse.reduce((prev, currVal) => Object.assign(prev, currVal), {})
    const newItem = await itemService.addItem(itemFormData)
    newItem.warehouse= newItem.warehouse.warehouseId
    const newItems = [newItem, ...items]
    setItems(newItems)
    navigate('/items')
  }

  const handleUpdateItem = async (itemFormData) => {
    let warehouseId = parseInt(itemFormData.warehouse)
    const itemWarehouse = warehouses.filter(warehouse => warehouse.warehouseId === warehouseId)
    itemFormData.warehouse = itemWarehouse.reduce((prev, currVal) => Object.assign(prev, currVal), {})
    const updatedItem = await itemService.updateItem(itemFormData)
    updatedItem.warehouse= updatedItem.warehouse.warehouseId
    const updatedItems = items.map((item) => (updatedItem.itemId === item.itemId ? updatedItem : item))
    setItems(updatedItems)
    navigate(`/items`)
  }

  const handleDeleteItem = async (itemId) => {
    console.log('Deleting item...')
    await itemService.deleteItem(itemId)
    console.log('Deleted item successfully.')
    const updatedItems= items.filter((item) => item.id !== parseInt(itemId))
    setItems(updatedItems)
    navigate('/items')
  }
  
  return (
    <>
      <NavBar/>
      <Routes>
        <Route path="/" element={<Home />}/>
        <Route path="/warehouses" element={<Warehouses warehouses={warehouses}/>}/>
        <Route path="warehouses/warehouse/add" element={<AddWarehouse handleAddWarehouse={handleAddWarehouse}/>}/>
        <Route path="warehouses/warehouse/:warehouseId/update" element={<UpdateWarehouse handleUpdateWarehouse={handleUpdateWarehouse}/>}/>
        <Route path="warehouses/warehouse/:warehouseId" element={<GetWarehouse handleDeleteWarehouse={handleDeleteWarehouse}/> } />
        <Route path="/items" element={<Items items={items}/>}/>
        <Route path="/items/item/add" element={<AddItem handleAddItem={handleAddItem} warehouses={warehouses}/>}/>
        <Route path="/items/item/:itemId/update" element={<UpdateItem handleUpdateItem={handleUpdateItem} warehouses={warehouses}/>}/>
        <Route path="/items/item/:itemId" element={<GetItem handleDeleteItem={handleDeleteItem}/>}/>
      </Routes>
    </>
  )
}

export default App;
