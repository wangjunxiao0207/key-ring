<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>加解密</title>
</head>
<body>
<div>
    <label>key:</label>
    <input id="key" value="12345678"/>
</div>
<div>
    <label>明文:</label>
    <input id="data" value="hello世界"/>
</div>
<div>
    <label>密文:</label>
    <textarea id="encrypt"></textarea>
</div>
<div>
    <label>解密:</label>
    <textarea id="decrypt"></textarea>
</div>
<div>
    <button id="encryptBtn" onclick="encryptStr();">加密</button>
    <button id="decryptBtn" onclick="decryptStr();">解密</button>
</div>
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script src="static/js/encrypto/tripledes.js"></script>
<script src="static/js/encrypto/components/mode-ecb.js"></script>
<script>
    //DES 解密 加密
    function encryptByDES(message, key) {
        var keyHex = CryptoJS.enc.Utf8.parse(key);
        var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        });
        return encrypted.toString();
    }
    //DES 解密
    function decryptByDES(ciphertext, key) {
        var keyHex = CryptoJS.enc.Utf8.parse(key);
        // direct decrypt ciphertext
        var decrypted = CryptoJS.DES.decrypt({
            ciphertext: CryptoJS.enc.Base64.parse(ciphertext)
        }, keyHex, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        });
        return decrypted.toString(CryptoJS.enc.Utf8);
    }

    //加密
    function encryptStr() {
        var strKey = $.trim($('#key').val());
        var strMsg = $.trim($('#data').val());
        $('#encrypt').val(encryptByDES(strMsg, strKey));
    }
    //解密
    function decryptStr() {
        var strKey = $.trim($('#key').val());
        var ciphertext = $.trim($('#data').val());
        $('#decrypt').val(decryptByDES(ciphertext, strKey));
    }
</script>
</body>
</html>
