/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja.utils;

import com.google.common.base.Strings;
import org.apache.commons.fileupload.ParameterParser;

public class HttpHeaderUtils {
    
    
    /**
     * A http content type should contain a character set like
     * "application/json; charset=utf-8".
     * 
     * If you only want to get "application/json" you can use this method.
     * 
     * See also: http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.7.1
     * 
     * @param rawContentType "application/json; charset=utf-8" or "application/json"
     * @return only the contentType without charset. Eg "application/json"
     */
    public static String getContentTypeFromContentTypeAndCharacterSetting(String rawContentType) {
        
        if (rawContentType.contains(";")) {
            return rawContentType.split(";")[0];           
        } else {
            return rawContentType;
        }
        
    }
    
    
    /**
     * A http content type should contain a character set like
     * "application/json; charset=utf-8".
     * 
     * If you only want to get the character set you can use this method.
     * 
     * See also: http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.7.1
     * 
     * @param rawContentType "application/json; charset=utf-8" or "application/json"
     * @param defaultEncoding returned when the content type has no or an empty charset parameter
     * @return only the character set like utf-8 OR the defaultEncoding when not set
     */
    public static String getCharsetOfContentType(String rawContentType, String defaultEncoding) {
        
        ParameterParser parser = new ParameterParser();
        parser.setLowerCaseNames(true);
        String charset = parser.parse(rawContentType, new char[] {';', ','}).get("charset");
        
        if (Strings.isNullOrEmpty(charset)) {
            return defaultEncoding;
        } else {
            return charset;
        }
        
    }
    
    /**
     * A http content type should contain a character set like
     * "application/json; charset=utf-8".
     * 
     * If you only want to get the character set you can use this method.
     * 
     * See also: http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.7.1
     * 
     * @param rawContentType "application/json; charset=utf-8" or "application/json"
     * @return only the character set like utf-8 OR utf-8 when not set
     */
    public static String getCharsetOfContentTypeOrUtf8(String rawContentType) {
        return getCharsetOfContentType(rawContentType, NinjaConstant.UTF_8);
  
    }

}
