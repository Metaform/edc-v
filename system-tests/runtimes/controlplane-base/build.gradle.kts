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

plugins {
    `java-library`
}

dependencies {
    runtimeOnly(project(":core:negotiation-manager"))
    runtimeOnly(project(":core:transfer-process-manager"))
    runtimeOnly(project(":extensions:banner-extension"))
    runtimeOnly(libs.edc.bom.controlplane) {
        exclude("org.eclipse.edc", "control-plane-contract-manager")
        exclude("org.eclipse.edc", "control-plane-transfer-manager")
    }
}



