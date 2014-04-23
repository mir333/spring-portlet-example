package eu.ibacz.sample.portlet.bascispring;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import static eu.ibacz.sample.portlet.bascispring.BasicSpringPortletConstants.*;

/**
 * This class is base controller for VIEW mode.
 */
@Controller
@RequestMapping("VIEW")
public class BasicSpringPortletViewController {
    protected final Logger LOG = Logger.getLogger(BasicSpringPortletViewController.class);

    @RenderMapping
    public String question() {
        return MAIN_VIEW;
    }

    @RenderMapping(params = PARAM_VIEW + "=" + GREETING)
    public String greeting(
            @RequestParam(NAME_PARAM) String name,
            Model model) {
        model.addAttribute(NAME_PARAM,name);
        return GREETING_VIEW;
    }

    @ActionMapping(TEST_ACTION)
    public void doAction(ActionRequest request, ActionResponse response) {
        LOG.warn("Processing name.");

        //order if these lines is important!
        response.setRenderParameters(request.getParameterMap());
        response.setRenderParameter(PARAM_VIEW, GREETING);
    }
}