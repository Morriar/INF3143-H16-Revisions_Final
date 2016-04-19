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
package ex1;

public class StringUtils {

    public static void main(String[] args) {
        System.out.println(countChars("Bonjour", 'z'));
        System.out.println(countChars("Bonjour", 'o'));
        System.out.println(countChars(null, 'o'));
    }

    public static int countChars(String input, char toCount) {
        assert input != null;
        int count = 0;
        for(char c : input.toCharArray()) {
            if(c == toCount) {
                count ++;
            }
        }
        return count;
    }
}
