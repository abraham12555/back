package la.kosmos.utils

class UrlContextAwareTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    
    def urlContextAware = {attr, body ->
        String path = attr.value
        def varName = attr.var
        if(!path.startsWith("/")){
            path = "/"+path
        }
        def res = resource()
        List<String> rootContext = ((String) res).tokenize("/")
        if(rootContext.isEmpty()){
            path = res + path
        }else{
            path = "/" + rootContext?.get(0) + path
        }
        set(var:varName, value:path)
    }
}
