"use strict";
const client = filestack.init(FILESTACKAPI);

const options = {
    fromSources: ["local_file_system", "url"],
    accept: ["image/*"],
    onUploadDone: function (res) {$("#avatar").val(res.filesUploaded[0].url)},
}

$("#upload-img").click(function(){
    client.picker(options).open();
});