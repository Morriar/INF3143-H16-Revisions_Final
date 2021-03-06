/*
 * Copyright 2015 Alexandre Terrasa <alexandre@moz-code.org>.
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
package ex2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateVar {

    String varString;

    public TemplateVar(String varString) throws IllegalTemplateVar {
        this.varString = varString;
        if (!checkVarString(varString)) {
            throw new IllegalTemplateVar();
        }
    }

    private boolean checkVarString(String varString) {
        return varString.matches("\\{\\{.+\\}\\}");
    }

    public String getVarName() {
        return varString.substring(2, varString.length() - 2);
    }

    public String getRegex() {
        String res = varString;
        res = res.replaceAll("\\{", "\\\\{");
        res = res.replaceAll("\\}", "\\\\}");
        return res;
    }

    @Override
    public String toString() {
        return varString;
    }
}
