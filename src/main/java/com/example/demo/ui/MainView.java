package com.example.demo.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

/**
 * This is the default (and only) view in this example.
 * <p>
 * It demonstrates how to create a form using Vaadin and the Binder. The backend
 * service and data class are in the <code>.data</code> package.
 */
@Route("")
public class MainView extends VerticalLayout {

    /**
     * We use Spring to inject the backend into our view
     */
    public MainView() {

        /*
         * Create the components we'll need
         */

        H3 title = new H3("Bank account");

        TextField idField = new TextField("Identifier of customer");
        
        Button queryButton = new Button("Query Account by Id");
        queryButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Span errorMessage = new Span();

        TextField nameField = new TextField("Name");
        nameField.setReadOnly(true);
        TextField balanceField = new TextField("Balance");
        balanceField.setReadOnly(true);
        
        TextField incrementField = new TextField("Increment");
        Button incrementButton = new Button("Increment");

        TextField withdrawField = new TextField("Withdraw");
        Button withdrawButton = new Button("Withdraw");

        FormLayout formLayout = new FormLayout(title, idField, queryButton, nameField,
                balanceField, incrementField, incrementButton, withdrawField, withdrawButton, errorMessage);

        // Restrict maximum width and center on page
        formLayout.setMaxWidth("500px");
        formLayout.getStyle().set("margin", "0 auto");

        // Allow the form layout to be responsive. On device widths 0-490px we have one
        // column, then we have two. Field labels are always on top of the fields.
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        // These components take full width regardless if we use one column or two (it
        // just looks better that way)
        formLayout.setColspan(title, 2);
        formLayout.setColspan(idField, 2);
        formLayout.setColspan(queryButton, 2);

        // Add some styles to the error message to make it pop out
        errorMessage.getStyle().set("color", "var(--lumo-error-text-color)");
        errorMessage.getStyle().set("padding", "15px 0");

        // Add the form to the page
        add(formLayout);

        /*
         * Set up form functionality
         */

        // And finally the submit button
        queryButton.addClickListener(e -> {}
        );

    }
}
