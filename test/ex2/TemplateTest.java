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

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class TemplateTest {

    Map<String, Object> input;

    @Before
    public void setUp() {
        input = new HashMap<>();
    }

    @Test
    public void template_has_vars() throws Exception {
        Template tpl = new Template("Bonjour {{var1}}!");
        tpl.parse();

        boolean res = tpl.hasVar("var1");
        assertTrue(res);
    }

    @Test
    public void empty_template_returns_empty() throws Exception {
        Template tpl = new Template("");
        tpl.parse();

        String res = tpl.render(input);
        String exp = "";
        assertEquals(exp, res);
    }

    @Test
    public void template_returns_replaced_string() throws Exception {
        Template tpl = new Template("Bonjour {{var1}}!");
        tpl.parse();

        input.put("var1", "le monde");
        String res = tpl.render(input);
        String exp = "Bonjour le monde!";
        assertEquals(exp, res);
    }

    @Test
    public void template_returns_replaced_multiple_string() throws Exception {
        Template tpl = new Template("Bonjour {{var1}} et {{var2}}!");
        tpl.parse();

        input.put("var1", "le monde");
        input.put("var2", "Alex");

        String res = tpl.render(input);
        String exp = "Bonjour le monde et Alex!";
        assertEquals(exp, res);
    }

    @Test(expected = IllegalTemplateInput.class)
    public void unknown_input_throws_exception() throws Exception {
        Template tpl = new Template("");
        tpl.parse();

        input.put("var1", "le monde");
        input.put("var2", "Alex");

        String res = tpl.render(input);
    }

    /*
    * Les deux tests suivants échouent.
    *
    * Ceci nous renseigne sur l'origine du bogue. En effet,
    * le matching de l'expression régulière contre la chaîne
    * "Bonjour {{var1}} et {{var2}}!", retourn une seule variable
    * `var1}} et {{var2` au lieu de deux.
    *
    * Il faudrait donc réécrire cette méthode de parsing pour bien
    * interpréter la chaîne comme ayant deux variables: var1 et var2.
    */


    @Test
    public void parse_lists_vars() throws Exception {
        Template tpl = new Template("Bonjour {{var1}} et {{var2}}!");
        tpl.parse();

        int res = tpl.vars.size();
        int exp = 2;

        assertEquals(exp, res);
    }

    @Test
    public void template_has_multiple_vars() throws Exception {
        Template tpl = new Template("Bonjour {{var1}} et {{var2}}!");
        tpl.parse();
        System.out.println(tpl.vars);
        boolean res1 = tpl.hasVar("var1");
        boolean res2 = tpl.hasVar("var2");
        assertTrue(res1 && res2);
    }

}
