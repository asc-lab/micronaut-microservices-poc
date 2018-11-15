package com.asc.lab.minicms.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;
import com.asc.lab.minicms.beans.Product;

/**
 * @version "$Id$"
 */

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/Product/")
public class ProductResource extends BaseRestResource {

    @GET
    @Path("/")
    public Pageable<Product> index(@Context HttpServletRequest request) {
        return findBeans(new DefaultRestContext(this, request), Product.class);
    }

    @GET
    @Path("/page/{page}")
    public Pageable<Product> page(@Context HttpServletRequest request, @PathParam("page") int page) {
        return findBeans(new DefaultRestContext(this, request, page, DefaultRestContext.PAGE_SIZE), Product.class);
    }

    @GET
    @Path("/page/{page}/{pageSize}")
    public Pageable<Product> pageForSize(@Context HttpServletRequest request, @PathParam("page") int page, @PathParam("pageSize") int pageSize) {
        return findBeans(new DefaultRestContext(this, request, page, pageSize), Product.class);
    }

}
