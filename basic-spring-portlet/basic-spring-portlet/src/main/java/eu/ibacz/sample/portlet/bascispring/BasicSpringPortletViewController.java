package eu.ibacz.sample.portlet.bascispring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * This class is base controller for VIEW mode.
 */
@Controller
@RequestMapping("VIEW")
public class BasicSpringPortletViewController {


    @RenderMapping
    public String doView() {
        return BasicSpringPortletConstants.MAIN_VIEW;
    }


}
