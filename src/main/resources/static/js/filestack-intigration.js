"use strict";
const client = filestack.init(FILESTACKAPI);

// options for picking single image
const options1 = {
    fromSources: ["local_file_system", "url"],
    accept: ["image/*"],
    onUploadDone: function (res) {$("#img").val(res.filesUploaded[0].url)},
}

// options for picking multiple images *requires refactor
const options2 = {
    fromSources: ["local_file_system", "url"],
    accept: ["image/*"],
    onUploadDone: function (res) {$("#img").val(res.filesUploaded[0].url)},
}

// single image selector
$("#upload-img").click(function(){
    client.picker(options1).open();
});