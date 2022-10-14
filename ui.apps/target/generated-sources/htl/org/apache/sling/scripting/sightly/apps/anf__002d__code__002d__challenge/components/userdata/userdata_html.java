/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 ******************************************************************************/
package org.apache.sling.scripting.sightly.apps.anf__002d__code__002d__challenge.components.userdata;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class userdata_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _dynamic_wcmmode = bindings.get("wcmmode");
Object _global_clientlib = null;
{
    Object var_testvariable0 = renderContext.getObjectModel().resolveProperty(_dynamic_wcmmode, "edit");
    if (renderContext.getObjectModel().toBoolean(var_testvariable0)) {
        out.write("Edit the user data component here ");
    }
}
out.write("\n");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
{
    Object var_templatevar1 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "all");
    {
        Object var_templateoptions2_field$_categories = (new Object[] {"anf.userdata"});
        {
            java.util.Map var_templateoptions2 = obj().with("categories", var_templateoptions2_field$_categories);
            callUnit(out, renderContext, var_templatevar1, var_templateoptions2);
        }
    }
}
out.write("\n\n  <div>\n  <form id=\"userForm\">\n\n\t\t<div id=\"success-message\" style=\"display: none; color: green\">Data\n\t\t\tsaved successfully</div>\n\t\t<div id=\"error-message\" style=\"display: none; color: red\"></div>\n\n    <label for=\"fname\">First Name</label>\n    <input type=\"text\" id=\"fname\" name=\"firstname\" placeholder=\"First Name\"/>\n\n    <label for=\"lname\">Last Name</label>\n    <input type=\"text\" id=\"lname\" name=\"lastname\" placeholder=\"Last Name\"/>\n\n    <label for=\"age\">Age</label>\n    <input type=\"text\" id=\"age\" name=\"age\" placeholder=\"Please enter age\"/>\n\n    <div>");
{
    Object var_resourcecontent3 = renderContext.call("includeResource", "country", obj().with("decorationTagName", "div").with("resourceType", "anf-code-challenge/components/country"));
    out.write(renderContext.getObjectModel().toString(var_resourcecontent3));
}
out.write("</div>\n\n  \n    <input type=\"submit\" value=\"Submit\"/>\n  </form>\n</div>\n\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

