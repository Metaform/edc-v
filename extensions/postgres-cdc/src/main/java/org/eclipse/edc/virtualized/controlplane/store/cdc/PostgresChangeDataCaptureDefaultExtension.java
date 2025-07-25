/*
 *  Copyright (c) 2025 Metaform Systems, Inc.
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Metaform Systems, Inc. - initial API and implementation
 *
 */

package org.eclipse.edc.virtualized.controlplane.store.cdc;

import org.eclipse.edc.connector.controlplane.contract.spi.types.negotiation.ContractNegotiationStates;
import org.eclipse.edc.connector.controlplane.transfer.spi.types.TransferProcessStates;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.runtime.metamodel.annotation.Provider;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.virtualized.controlplane.contract.spi.negotiation.ContractNegotiationChangeListener;
import org.eclipse.edc.virtualized.controlplane.contract.spi.negotiation.ContractNegotiationStateMachineService;
import org.eclipse.edc.virtualized.controlplane.transfer.spi.TransferProcessChangeListener;
import org.eclipse.edc.virtualized.controlplane.transfer.spi.TransferProcessStateMachineService;

public class PostgresChangeDataCaptureDefaultExtension implements ServiceExtension {

    @Inject
    private ContractNegotiationStateMachineService stateMachineService;

    @Inject
    private TransferProcessStateMachineService transferProcessStateMachineService;

    @Provider(isDefault = true)
    public ContractNegotiationChangeListener negotiationChangeListener() {
        return (before, after) -> {
            var state = ContractNegotiationStates.from(after.getState());
            return stateMachineService.handle(after.getId(), state);
        };
    }

    @Provider(isDefault = true)
    public TransferProcessChangeListener transferProcessChangeListener() {
        return (before, after) -> {
            var state = TransferProcessStates.from(after.getState());
            return transferProcessStateMachineService.handle(after.getId(), state);
        };
    }
}
