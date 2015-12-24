package com.tom.demo


/**.
 * Anthor: tom
 * Date: 2015/12/23 0023 
 * Time: 13:18 
 */
class Syntax {
   static void main(String[] args){
       //lazy interpolate
       def number = 1
       def eagerGString = "value == ${number}"
       def lazyGString = "value == ${ -> number }"

       assert eagerGString == "value == 1"
       assert lazyGString ==  "value == 1"

       number = 2
       assert eagerGString == "value == 1"
       assert lazyGString ==  "value == 2"

       //跟java互相操作
       def message = "The message is ${'hello'}"
       assert message instanceof GString

       def result = takeString(message)
       assert result instanceof String
       assert result == 'The message is hello'


       //GString and Java String hashCode
       assert "one: ${1}".hashCode() != "one: 1".hashCode()


       def name = "Guillaume"
       def date = "April, 1st"

       def dollarSlashy = $/
    Hello $name,
    today we're ${date}.

    $ dollar sign
    $$ escaped dollar sign
    \ backslash
    / forward slash
    $/ escaped forward slash
    $/$ escaped dollar slashy string delimiter
/$

       assert [
               'Guillaume',
               'April, 1st',
               '$ dollar sign',
               '$ escaped dollar sign',
               '\\ backslash',
               '/ forward slash',
               '$/ escaped forward slash',
               '/$ escaped dollar slashy string delimiter'

       ].each { dollarSlashy.contains(it);println(it) }
   }

    static String takeString(String message) {
        assert message instanceof String
        return message
    }

}
