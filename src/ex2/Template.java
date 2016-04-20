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

import com.google.java.contract.Ensures;
import com.google.java.contract.Requires;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {

    String templateString;

    Map<String, TemplateVar> vars;

    boolean isParsed = false;

    @Requires("templateString != null")
    @Ensures("this.templateString != null")
    public Template(String templateString) {
        this.templateString = templateString;
    }

    @Requires("this.templateString != null")
    @Ensures({"this.isParsed", "this.vars != null"})
    public void parse() throws IllegalTemplateVar {
        vars = new HashMap<>();
        Pattern re = Pattern.compile("\\{\\{.+\\}\\}");
        Matcher me = re.matcher(templateString);
        while (me.find()) {
            registerVar(me.group());
        }
        isParsed = true;
    }

    @Requires("this.vars != null")
    @Ensures("this.vars.size() == old(this.vars).size() + 1")
    public TemplateVar registerVar(String varString) throws IllegalTemplateVar {
        TemplateVar var = new TemplateVar(varString);
        vars.put(var.getVarName(), var);
        return var;
    }

    public boolean hasVar(String varName) {
        return vars.containsKey(varName);
    }

    @Requires("hasVar(varName)")
    public TemplateVar getVar(String varName) {
        return vars.get(varName);
    }

    @Requires("isParsed")
    @Ensures("result != null")
    public String render(Map<String, Object> values) throws IllegalTemplateInput {
        String output = templateString;
        for (String k : values.keySet()) {
            if (!hasVar(k)) {
                throw new IllegalTemplateInput();
            }
            TemplateVar var = getVar(k);
            output = replaceVar(var, values.get(k));
        }
        return output;
    }

    private String replaceVar(TemplateVar var, Object value) {
        return templateString.replaceAll(var.getRegex(), value.toString());
    }

}
