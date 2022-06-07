"use strict";

const client = filestack.init(FILESTACKAPI);
let value = null;

const options = {
    fromSources: ["local_file_system", "url"],
    accept: ["image/jpeg"],
    onFileUploadFinished: file => {
        value = file.originalPath;
        document.getElementById("imgUrl").value = value;
    }
}

document.getElementById("dragDrop").onclick = function (){
    client.picker(options).open();
}

// <div id="dragDrop" className="btn btn-warning"></div>
// <input th:field="*{}" type="hidden">