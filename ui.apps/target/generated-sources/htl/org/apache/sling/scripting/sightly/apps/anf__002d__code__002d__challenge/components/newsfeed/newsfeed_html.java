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
package org.apache.sling.scripting.sightly.apps.anf__002d__code__002d__challenge.components.newsfeed;

import java.io.PrintWriter;
import java.util.Collection;
import javax.script.Bindings;

import org.apache.sling.scripting.sightly.render.RenderUnit;
import org.apache.sling.scripting.sightly.render.RenderContext;

public final class newsfeed_html extends RenderUnit {

    @Override
    protected final void render(PrintWriter out,
                                Bindings bindings,
                                Bindings arguments,
                                RenderContext renderContext) {
// Main Template Body -----------------------------------------------------------------------------

Object _dynamic_wcmmode = bindings.get("wcmmode");
Object _global_clientlib = null;
Object _global_myobject = null;
Collection var_collectionvar3_list_coerced$ = null;
{
    Object var_testvariable0 = renderContext.getObjectModel().resolveProperty(_dynamic_wcmmode, "edit");
    if (renderContext.getObjectModel().toBoolean(var_testvariable0)) {
        out.write("Edit News Feed component here");
    }
}
out.write("\n");
_global_clientlib = renderContext.call("use", "/libs/granite/sightly/templates/clientlib.html", obj());
{
    Object var_templatevar1 = renderContext.getObjectModel().resolveProperty(_global_clientlib, "css");
    {
        Object var_templateoptions2_field$_categories = (new Object[] {"anf.newsfeed"});
        {
            java.util.Map var_templateoptions2 = obj().with("categories", var_templateoptions2_field$_categories);
            callUnit(out, renderContext, var_templatevar1, var_templateoptions2);
        }
    }
}
out.write("\n\n");
_global_myobject = renderContext.call("use", com.anf.core.models.NewsFeedModel.class.getName(), obj());
out.write("<div>\n\n    <section class=\"recentNews\">\n        <div class=\"container\">\n            <h2 class=\"news-title\">Recent News</h2>\n            ");
{
    Object var_collectionvar3 = renderContext.getObjectModel().resolveProperty(_global_myobject, "newsFeed");
    {
        long var_size4 = ((var_collectionvar3_list_coerced$ == null ? (var_collectionvar3_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar3)) : var_collectionvar3_list_coerced$).size());
        {
            boolean var_notempty5 = (var_size4 > 0);
            if (var_notempty5) {
                {
                    long var_end8 = var_size4;
                    {
                        boolean var_validstartstepend9 = (((0 < var_size4) && true) && (var_end8 > 0));
                        if (var_validstartstepend9) {
                            out.write("<div>");
                            if (var_collectionvar3_list_coerced$ == null) {
                                var_collectionvar3_list_coerced$ = renderContext.getObjectModel().toCollection(var_collectionvar3);
                            }
                            long var_index10 = 0;
                            for (Object newslist : var_collectionvar3_list_coerced$) {
                                {
                                    boolean var_traversal12 = (((var_index10 >= 0) && (var_index10 <= var_end8)) && true);
                                    if (var_traversal12) {
                                        out.write("\n\n                <div class=\"row\">\n\n                    <div class=\"ct-blog col-sm-6 col-md-4\">\n                        <div class=\"inner\">\n                            <div class=\"fauxcrop\">\n                                <a href=\"#\"><img alt=\"News Entry\"");
                                        {
                                            Object var_attrvalue13 = renderContext.getObjectModel().resolveProperty(newslist, "urlImage");
                                            {
                                                Object var_attrcontent14 = renderContext.call("xss", var_attrvalue13, "uri");
                                                {
                                                    Object var_shoulddisplayattr16 = ((renderContext.getObjectModel().toBoolean(var_attrcontent14) ? var_attrcontent14 : ("false".equals(var_attrvalue13))));
                                                    if (renderContext.getObjectModel().toBoolean(var_shoulddisplayattr16)) {
                                                        out.write(" src");
                                                        {
                                                            boolean var_istrueattr15 = (var_attrvalue13.equals(true));
                                                            if (!var_istrueattr15) {
                                                                out.write("=\"");
                                                                out.write(renderContext.getObjectModel().toString(var_attrcontent14));
                                                                out.write("\"");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        out.write("/></a>\n                            </div>\n                            <div class=\"ct-blog-content\">\n                                <div class=\"ct-blog-date\">\n                                    <span>");
                                        {
                                            String var_17 = ((((" " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(newslist, "currentdate"), "text"))) + " || ") + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(newslist, "author"), "text"))) + " ");
                                            out.write(renderContext.getObjectModel().toString(var_17));
                                        }
                                        out.write("</span><strong>1</strong>\n                                </div>\n                                <h3 class=\"ct-blog-header\">");
                                        {
                                            String var_18 = ("\n                                    " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(newslist, "title"), "text")));
                                            out.write(renderContext.getObjectModel().toString(var_18));
                                        }
                                        out.write("<br/>\n                                </h3>\n                                <div class=\"ct-blog-dec\">\n                                    <span>");
                                        {
                                            String var_19 = ("  " + renderContext.getObjectModel().toString(renderContext.call("xss", renderContext.getObjectModel().resolveProperty(newslist, "Description"), "text")));
                                            out.write(renderContext.getObjectModel().toString(var_19));
                                        }
                                        out.write("</span><strong>1</strong>\n                                </div>\n                            </div>\n                        </div>\n                    </div>\n                </div>\n            ");
                                    }
                                }
                                var_index10++;
                            }
                            out.write("</div>");
                        }
                    }
                }
            }
        }
    }
    var_collectionvar3_list_coerced$ = null;
}
out.write("\n\n            <div class=\"btn-news btn-contests\">\n                <a href=\"#\">VIEW ALL NEWS</a>\n            </div>\n        </div>\n    </section>\n\n</div>\n<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\"/>\n<link href=\"recent-news-boxes.css\" rel=\"stylesheet\"/>\n<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>\n<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");


// End Of Main Template Body ----------------------------------------------------------------------
    }



    {
//Sub-Templates Initialization --------------------------------------------------------------------



//End of Sub-Templates Initialization -------------------------------------------------------------
    }

}

