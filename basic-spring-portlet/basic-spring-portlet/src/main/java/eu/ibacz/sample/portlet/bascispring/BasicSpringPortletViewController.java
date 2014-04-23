package eu.ibacz.sample.portlet.bascispring;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * This class is base controller for VIEW mode.
 */
@Controller
@RequestMapping("VIEW")
public class BasicSpringPortletViewController {
    protected final Logger LOG = Logger.getLogger(BasicSpringPortletViewController.class);

    @RenderMapping
    public String doView() {
        return BasicSpringPortletConstants.MAIN_VIEW;
    }

    @ActionMapping(BasicSpringPortletConstants.TEST_ACTION)
    public void doAction(@RequestParam(BasicSpringPortletConstants.NAME_PARAM) String name) {
        LOG.warn("I've got name " + name);
    }


}
