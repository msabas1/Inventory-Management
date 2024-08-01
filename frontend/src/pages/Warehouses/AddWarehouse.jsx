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
          <form onSubmit={handleSubmit}>
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
            <button type="submit" className="formButton">Submit</button>
          </form>
        </div>
      </main>
    </>
   );
}
 
export default AddWarehouse;