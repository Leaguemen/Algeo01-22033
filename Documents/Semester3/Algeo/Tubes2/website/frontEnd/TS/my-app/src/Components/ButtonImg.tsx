import React, { ChangeEvent, useState } from "react";

function ImgButton() {
  const [selectedImage, setSelectedImage] = useState(null);

  const handleFileChange = (e: ChangeEvent<HTMLInputElement>) => {
    const selectedFiles = e.target.files;
    if (selectedFiles && selectedFiles.length > 0) {
      const reader = new FileReader();
      // Process each selected file and upload them to the server.
      const file = selectedFiles[0];
      // Your file handling logic here
      setSelectedImage(URL.createObjectURL(file) || null);
    }
  };

  return (
    <div>
      <input type="file" accept="image/*" onChange={handleFileChange} />
      {selectedImage && (
        <img
          src={selectedImage}
          alt="Selected Image"
          style={{ maxWidth: "100%" }}
        />
      )}
    </div>
  );
}

export default ImgButton;
