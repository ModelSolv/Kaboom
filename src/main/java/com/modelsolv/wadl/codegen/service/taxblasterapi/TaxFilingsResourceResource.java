/**
 * Created by Apache CXF WadlToJava code generator
**/
package com.modelsolv.wadl.codegen.service.taxblasterapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.modelsolv.reprezen.schemas.taxblasterapi.TaxFilingsResource;

@Path("taxFilings")
public interface TaxFilingsResourceResource {

    @GET
    @Produces("application/xml")
    TaxFilingsResource getTaxFilingsResource();

}