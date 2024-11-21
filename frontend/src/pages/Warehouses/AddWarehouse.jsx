import { useState } from "react";

const AddWarehouse = (props) => {
  const [formData, setFormData] = useState({
    capacity: '',
    warehouseName: ''
  })

  const handleChange = (evt) => {
    const { name, value } = evt.target
    setFormData({ ...formData, [name]: value })
  }
  
  const handleSubmit = (evt) => {
    evt.preventDefault()
    props.handleAddWarehouse(formData)
  }

  return ( 
    <>
      <main >
        <h1>Add Warehouse</h1>
        <div>
          <form onSubmit={handleSubmit} id="add-warehouse-form-modal">
          <fieldset>
              <legend>Name</legend>
              <input
                required
                type="text"
                name="warehouseName"
                value={formData.warehouseName}
                onChange={handleChange}
                placeholder="Enter warehouse name"
                autoComplete="off"
              />
            </fieldset>
            <fieldset >
              <legend >Capacity</legend>
              <input
                required
                type="text"
                name="capacity"
                value={formData.capacity}
                onChange={handleChange}
                placeholder="Enter warehouse capacity"
                autoComplete="off"
              />
            </fieldset>
            <button aria-label="Submit Add Warehouse" type="submit" className="formButton" id="submit-add-warehouse-btn">Submit</button>
          </form>
        </div>
      </main>
    </>
   );
}
 
export default AddWarehouse;