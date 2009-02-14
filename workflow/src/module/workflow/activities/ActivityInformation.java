/*
 * @(#)ActivityInformation.java
 *
 * Copyright 2009 Instituto Superior Tecnico
 * Founding Authors: João Figueiredo, Luis Cruz, Paulo Abrantes, Susana Fernandes
 * 
 *      https://fenix-ashes.ist.utl.pt/
 * 
 *   This file is part of the MyOrg web application infrastructure.
 *
 *   MyOrg is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published
 *   by the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.*
 *
 *   MyOrg is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with MyOrg. If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package module.workflow.activities;

import java.io.Serializable;

import module.workflow.domain.WorkflowProcess;
import pt.ist.fenixWebFramework.util.DomainReference;

public class ActivityInformation<P extends WorkflowProcess> implements Serializable {

    private DomainReference<P> process;
    private String activityName;
    private boolean confirmationNeeded;
    private boolean confirmed;
    private String localizedMessage;

    public boolean isConfirmationNeeded() {
	return confirmationNeeded;
    }

    public void setConfirmationNeeded(boolean confirmationNeeded) {
	this.confirmationNeeded = confirmationNeeded;
    }

    public String getLocalizedMessage() {
	return localizedMessage;
    }

    public void setLocalizedMessage(String localizedMessage) {
	this.localizedMessage = localizedMessage;
    }

    public String getActivityName() {
	return activityName;
    }

    public void setActivity(WorkflowActivity<? extends WorkflowProcess, ? extends ActivityInformation> activity) {
	this.activityName = activity.getName();
    }

    public ActivityInformation(P process, WorkflowActivity<? extends WorkflowProcess, ? extends ActivityInformation> activity) {
	setProcess(process);
	setActivity(activity);
	setConfirmationNeeded(activity.needsConfirmation());
	setConfirmed(!activity.needsConfirmation());
	setLocalizedMessage(activity.getLocalizedConfirmationMessage());
    }

    public P getProcess() {
	return process.getObject();
    }

    public void setProcess(P process) {
	this.process = new DomainReference<P>(process);
    }

    public boolean hasAllneededInfo() {
	return true;
    }

    public boolean isConfirmed() {
	return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
	this.confirmed = confirmed;
    }
}