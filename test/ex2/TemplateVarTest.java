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

import org.junit.Test;
import static org.junit.Assert.*;

public class TemplateVarTest {

    @Test
    public void getVarName_accepts_a_specific_format() throws IllegalTemplateVar {
        TemplateVar var = new TemplateVar("{{varName}}");
    }

    @Test(expected = IllegalTemplateVar.class)
    public void getVarName_throws_exception_if_wrong_format() throws IllegalTemplateVar {
        TemplateVar var = new TemplateVar(null);
    }

    @Test(expected = IllegalTemplateVar.class)
    public void getVarName_throws_exception_if_wrong_format2() throws IllegalTemplateVar {
        TemplateVar var = new TemplateVar("{{}}");
    }

    @Test(expected = IllegalTemplateVar.class)
    public void getVarName_throws_exception_if_wrong_format3() throws IllegalTemplateVar {
        TemplateVar var = new TemplateVar("varName");
    }

    @Test
    public void getVarName_returns_string_without_braces() throws IllegalTemplateVar {
        String exp = "varName";
        TemplateVar var = new TemplateVar("{{" + exp + "}}");
        String res = var.getVarName();
        assertEquals(exp, res);
    }

    @Test
    public void getVarName_preserves_case() throws IllegalTemplateVar {
        String exp = "VARNAME";
        TemplateVar var = new TemplateVar("{{" + exp + "}}");
        String res = var.getVarName();
        assertEquals(exp, res);
    }

    @Test
    public void getVarName_accepts_nested_braces() throws IllegalTemplateVar {
        String valid = "{{{{varName}}}}";
        TemplateVar var = new TemplateVar(valid);
        String exp = "{{varName}}";
        String res = var.getVarName();
        assertEquals(exp, res);
    }

    @Test
    public void getRegexp_returns_escaped_varstring() throws IllegalTemplateVar {
        String valid = "{{VAR1}}";
        TemplateVar var = new TemplateVar(valid);
        String exp = "\\{\\{VAR1\\}\\}";
        String res = var.getRegex();
        assertEquals(exp, res);
    }
}
