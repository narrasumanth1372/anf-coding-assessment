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
package org.apache.sling.scripting.sightly.apps.anf__002d__code__002d__challenge.components.preferences;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class preferences_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _global_template = null;
Object _dynamic_properties = bindings.get("properties");
_global_template = renderContext.call("use", "core/wcm/components/commons/v1/templates.html", obj());
out.write("\n");
{
    Object var_templatevar0 = renderContext.getObjectModel().resolveProperty(_global_template, "placeholder");
    {
        boolean var_templateoptions1_field$_isempty = (!renderContext.getObjectModel().toBoolean(renderContext.getObjectModel().resolveProperty(_dynamic_properties, "name")));
        {
            java.util.Map var_templateoptions1 = obj().with("isEmpty", var_templateoptions1_field$_isempty);
            callUnit(out, renderContext, var_templatevar0, var_templateoptions1);
        }
    }
}
out.write("\n\n");
{
    Object var_testvariable2 = renderContext.getObjectModel().resolveProperty(_dynamic_properties, "name");
    if (renderContext.getObjectModel().toBoolean(var_testvariable2)) {
        out.write("\n    <h3>");
        {
            String var_3 = ("Name: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "name"), "text")));
            out.write(renderContext.getObjectModel().toString(var_3));
        }
        out.write("</h3>\n    <h3>");
        {
            String var_4 = ("Country: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "country"), "text")));
            out.write(renderContext.getObjectModel().toString(var_4));
        }
        out.write("</h3>\n    <h3");
        {
            String var_attrcontent5 = (("color: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "color"), "styleString"))) + ";");
            {
                Object var_shoulddisplayattr6 = ((renderContext.getObjectModel().toBoolean(var_attrcontent5) ? var_attrcontent5 : ("false".equals(var_attrcontent5))));
                if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr6)) {
                    out.write(" style=\"");
                    out.write(renderContext.getObjectModel().toString(var_attrcontent5));
                    out.write("\"");
                }
            }
        }
        out.write(">");
        {
            String var_7 = ("Favourite color: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "color"), "text")));
            out.write(renderContext.getObjectModel().toString(var_7));
        }
        out.write("</h3>\n    <h3");
        {
            String var_attrcontent8 = (("font-size: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "font"), "styleString"))) + ";");
            {
                Object var_shoulddisplayattr9 = ((renderContext.getObjectModel().toBoolean(var_attrcontent8) ? var_attrcontent8 : ("false".equals(var_attrcontent8))));
                if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr9)) {
                    out.write(" style=\"");
                    out.write(renderContext.getObjectModel().toString(var_attrcontent8));
                    out.write("\"");
                }
            }
        }
        out.write(">");
        {
            String var_10 = ("Favourite font: " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(_dynamic_properties, "font"), "text")));
            out.write(renderContext.getObjectModel().toString(var_10));
        }
        out.write("</h3>\n");
    }
}


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

