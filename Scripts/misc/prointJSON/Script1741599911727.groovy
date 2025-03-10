import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

String json = '''{
    "foo": 123.456,
    "bar": "hello, world!",
    "baz": true,
    "values": [ 3 1 4 1 5 9 ]
}'''

WebUI.comment("<pre>" + json + "</pre>")