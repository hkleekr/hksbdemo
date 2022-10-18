/**
 * ajax 공통모듈
 * ------------------

 */

/**
 * Ajax 콜 함수
 * @param: _url: api 주소
 * @param: _param: parameters
 * @param: _callback: 성공시 callback 함수
 * @param: _httpMethod: GET, POST, PUT, DELETE
 * @param: _errMsg: Error Msg
 * @param: _spanner: spanner class  "div.spanner"
 * @param: _overlay: overlay class  "div.overlay"
 * @param: _className: add remove class name "show"

 */
const fetchAjax = (_url, _contentType, _param, _callback, _httpMethod, _errMsg, _spanner, _overlay, _className) => {

    if (_spanner) {
        classAddRemove(_spanner, "add", _className);
        classAddRemove(_overlay, "add", _className);
    }
    if (!_httpMethod) _httpMethod = 'GET'

    fetch(_url, {
        method: _httpMethod,
        body: _param,
        headers : _contentType
    })
        .then((response) => {
                if (response.ok) {
                    return response.json()
                }
            })
        //콜백함수
        .then(_callback)

        .catch((error) => alert(_errMsg + "" + error))

        .finally(() => {
            if (_spanner) {
                classAddRemove(_spanner, "remove", _className);
                classAddRemove(_overlay, "remove", _className);
            }
        })
}

const classAddRemove = (_spinnerClass, _addRe, _className) => {
    if (_addRe == "add") {
        document.querySelector(_spinnerClass).classList.add(_className);
    } else if (_addRe == "remove") {
        document.querySelector(_spinnerClass).classList.remove(_className);
    }
}
/**
 * jquery serialize
 *@param: _selector: form id or class
 */
const serialized = (_selector) => {
    let form = document.querySelector(_selector);
    let field, s = [];
    if (typeof form == 'object' && form.nodeName == "FORM") {
        let len = form.elements.length;
        for (i = 0; i < len; i++) {
            field = form.elements[i];
            if (field.name && /*!field.disabled &&*/ field.type != 'file' && field.type != 'reset' && field.type != 'submit' && field.type != 'button') {
                if (field.type == 'select-multiple') {
                    for (j = form.elements[i].options.length - 1; j >= 0; j--) {
                        if (field.options[j].selected)
                            s[s.length] = encodeURIComponent(field.name) + "=" + encodeURIComponent(field.options[j].value).replace(/%20/g, '+');
                    }
                } else if ((field.type != 'checkbox' && field.type != 'radio') || field.checked) {
                    s[s.length] = encodeURIComponent(field.name) + "=" + encodeURIComponent(field.value).replace(/%20/g, '+');
                }
            }
        }
    }
    return s.join('&').replace(/%20/g, '+');
}
